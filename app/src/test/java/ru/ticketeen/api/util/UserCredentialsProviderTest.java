package ru.ticketeen.api.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.ticketeen.preference.LoginPasswordPreference;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserCredentialsProviderTest {

    private static final String LOGIN = "+79139066994";
    private static final String PASSWORD = "705697";

    @Mock
    private LoginPasswordPreference preference;

    @Test
    public void getUserCredentials() throws Exception {
        when(preference.getLogin()).thenReturn(LOGIN);
        when(preference.getPassword()).thenReturn(PASSWORD);

        final UserCredentialsProvider provider = new UserCredentialsProvider(preference);
        final String userCredentials = provider.getUserCredentials();
        assertEquals("Basic Kzc5MTM5MDY2OTk0OjcwNTY5Nw==", userCredentials);
    }

}