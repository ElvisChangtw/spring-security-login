package com.example.demo.securingweb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.impl.AppUserDetailsServiceImpl;
import com.example.demo.service.impl.PermissionServiceImpl;
import com.example.demo.util.UserAuthorityUtils;

import lombok.SneakyThrows;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled=true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)  // 這個要打開才有支援方法層級的驗證如@PostFilter
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	private final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	@Autowired DataSource dataSource;
	@Autowired AppUserDetailsServiceImpl appUserDetailsService;
	@Autowired PermissionServiceImpl permissionService;
	@Autowired UserAuthorityUtils utils;
	final String[] publicEndPoints = {"/", "/home", "/public"};

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		// 越嚴格的條件要放越前面, 因此若有anyRequest().permitAll()要放在最後面
		http
			.authorizeRequests()
//				.antMatchers(utils.getUrlsFromDb("ADMIN")).hasRole("ADMIN")
//				.antMatchers(utils.getUrlsFromDb("STAFF")).hasRole("STAFF")
//				.antMatchers(utils.getUrlsFromDb("USER")).hasRole("USER")
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers(publicEndPoints).permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic().and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.exceptionHandling().accessDeniedPage("/accessDenied");
	}
	
	@Autowired
	@SneakyThrows
	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		// 記憶體內置user
		// 法一, 設定一組基本帳密
		 auth.inMemoryAuthentication()
		 .passwordEncoder(new BCryptPasswordEncoder())  		// 使用bcrypt加密
//		 .passwordEncoder(NoOpPasswordEncoder.getInstance())	// 不使用加密
	     .withUser("administrator")			// 用户名
	     .password("administrator")			// 密碼
//	     .password("{noop}administrator")	// {noop}表示密碼為明文，
//	     .roles("ADMIN")			// admin角色，一般全大寫
	     .authorities("admin");		// 管理user權限，一般小寫，role與authority一起用會後蓋前
		
		 // 法二, 透過userDetailsServicde另外設定從DB來的帳號密碼
		 auth //Builder Design Pattern
		.userDetailsService(appUserDetailsService) //換成userDetailsService
		.passwordEncoder(new BCryptPasswordEncoder()); //登入時使用bcyrpt處理密碼再與db比對
		
		 // 法三, 透過jdbcAuthentication方法
//		 auth.jdbcAuthentication().dataSource(dataSource)
//         .usersByUsernameQuery("select username, password, enabled from Users " + "where username=?")
//         .authoritiesByUsernameQuery("select username, authority from UserAuthorities " + "where username=?")
//         .passwordEncoder(new BCryptPasswordEncoder());
		 
	}
	
//	要有這段才能讀到resource內的css file
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
	        .ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }
}
