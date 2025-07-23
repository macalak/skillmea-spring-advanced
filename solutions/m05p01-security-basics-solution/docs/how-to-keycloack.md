# Setup Keycloak
* New realm
* New client
* New user

# Build dependencies

implementation ('org.springframework.security:spring-security-oauth2-resource-server')

implementation ('org.springframework.security:spring-security-oauth2-jose')

# Add configuration

application.properties:
```
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=${keycloak.auth-server-url}realms/siemens/protocol/openid-connect/certs
```

Spring CFG class:

```
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) {
		try {
			http.authorizeRequests()
					.antMatchers("/library/**").fullyAuthenticated()
					.anyRequest().permitAll()
                    .and()
                    .formLogin().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .oauth2ResourceServer()
                    .jwt()
					.jwtAuthenticationConverter(new JwtAuthenticationConverter());
    	} catch(Exception e) {
    		LOG.error(LoggerMarkers.SECURITY, "Error during http security configuration.", e);
    	}
    }
	
	/**
	 * Extracts realm_access roles from an access token.
	 */
	public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
		private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

		@Override
		public AbstractAuthenticationToken convert(final Jwt source) {
			Collection<GrantedAuthority> authorities = Stream.concat(defaultGrantedAuthoritiesConverter.convert(source)
							.stream(),
					extractResourceRoles(source).stream())
					.collect(Collectors.toSet());
			return new JwtAuthenticationToken(source, authorities);
		}

		private  Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
			Map<String, Object> realmAccess = jwt.getClaim("realm_access");
			Collection<String> resourceRoles;
			if (realmAccess != null && (resourceRoles = (Collection<String>) realmAccess.get("roles")) != null )
				return resourceRoles.stream()
						.map(x -> new SimpleGrantedAuthority( x))
						.collect(Collectors.toSet());
			return Collections.emptySet();
		}
	}
}
```