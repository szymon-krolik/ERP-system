package sample.rest;

import sample.dto.OperatorCredentialsDto;

public class AuthenticatorImpl implements Authenticator {

    private static final String LOGIN = "skuser";
    private static final String PASSWORD = "skpassword";

    @Override
    public void authenticate(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = createAuthenticationTask(operatorCredentialsDto, authenticationResultHandler);
        Thread authenticationThread = new Thread(authenticationTask);
        authenticationThread.setDaemon(true);
        authenticationThread.start();
    }

    private Runnable createAuthenticationTask(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
        return () -> {
            try {
                Thread.sleep(1000);
                boolean authenticated = LOGIN.equals(operatorCredentialsDto.getLogin()) && PASSWORD.equals(operatorCredentialsDto.getPassword());
                authenticationResultHandler.handle(authenticated);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
