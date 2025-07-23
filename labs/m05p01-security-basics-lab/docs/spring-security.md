# Spring Security
The Spring Security framework enables you to add following concepts into your application in painless way:

* Authentication - from basic HTTP auth to OAuth2/OIDC and beyond
* Access Control (Authorization) - based on roles (RBAC), attributes (ABAC), and more   
* Protect the business logic and data from unauthorized access
* Protect application from common attacks - OWASP  
* With standards ready implementation

## Authentication
The Spring Security framework defines main interface *AuthenticationManager* for various authentication strategies.
There are someprovidedby framework, but you can implement yout own *AuthenticationManager*. Recommendation for you
is to first check if there is existing implementation and then decise to go for customization. You should always
try to stick with standards.

```Java
public interface AuthenticationManager {

  Authentication authenticate(Authentication authentication)
    throws AuthenticationException;
}
```

The *AuthenticationManager* interface define *authenticate()* method with the *Authentication* argument (holds principal to be authenticated). If Authentication fails, the runtime *AuthenticationException* is thrown. Usually the *AuthenticationManager*
delegates the authentication logic to one or more providers of type *AuthenticationProvider*. There are providers which 
supports certain authentication method. So you can configure chain of providers for multifactor authentication (MFA) for instance.

```Java
public interface AuthenticationProvider {

	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;

	boolean supports(Class<?> authentication);
}
```

One example of Authentication Providers one which retrieves principal details form data store as SQL database. 
The *authentication* object is typically in form of token as *UsernamePasswordAuthenticationToken*, *JwtAuthenticationToken*, and others.

You can plug-in custom *AuthenticationProvider* using *AuthenticationManagerBuilder*. For example like this:

```Java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    } 

    [...]
} 
```

Refer more: https://docs.spring.io/spring-security/reference/servlet/authentication/index.html

## Access Control
The Spring Security framework defines the *AuthorizationManager* interface for access control strategy implementation. 
The access decision logic is based on the *Authentication* object to access authenticated principal and *Object*,
which is protected. The *verify()* method determines if access should be granted by calling *check()* method..

```Java
@FunctionalInterface
public interface AuthorizationManager<T> {

	default void verify(Supplier<Authentication> authentication, T object) {
		AuthorizationDecision decision = check(authentication, object);
		if (decision != null && !decision.isGranted()) {
			throw new AccessDeniedException("Access Denied");
		}
	}

	@Nullable
	AuthorizationDecision check(Supplier<Authentication> authentication, T object);
}
```

The *Athentication* also holds the list of *GrantedAuthority* objects which are granted to principal. You can imagine
granted authority as role (e.g. ROLE_ADMIN), or any other claim. The access decision is applied by Interceptors provided
by the Spring Security framework. Interceptors can intercept any (configured) method invocation, web request, or message.
There are several *AuthorizationManager* implementation provided by the framework. You can even implement your own.

* AuthorityAuthorizationManager
* AuthenticatedAuthorizationManager

Refer more: https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html


## Protection against exploits
The Spring Security framework provides of the box support for protecting applications (Servlet and WebFlux) against common exploits.

* Cross Site Request Forgery ([CSRF](https://owasp.org/www-community/attacks/csrf))
* Security HTTP [Response Headers](https://docs.spring.io/spring-security/reference/features/exploits/headers.html)
    * Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    * Pragma: no-cache
    * Expires: 0
    * X-Content-Type-Options: nosniff
    * Strict-Transport-Security: max-age=31536000 ; includeSubDomains
    * X-Frame-Options: DENY
    * X-XSS-Protection: 0


## Components of Auth flow

![spring_auth_flow](assets/spring-authentication.jpg)