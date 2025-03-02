package dw.example.application.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class AppBasicAuthenticator implements Authenticator<BasicCredentials, User> {

  private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
      "guest", ImmutableSet.of(),
      "user", ImmutableSet.of("USER"),
      "manager", ImmutableSet.of("MANAGER", "USER")
  );

  @Override
  public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
    if (VALID_USERS.containsKey(credentials.getUsername())
        && "password".equals(credentials.getPassword())) {
      return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
    }
    return Optional.empty();
  }
}
