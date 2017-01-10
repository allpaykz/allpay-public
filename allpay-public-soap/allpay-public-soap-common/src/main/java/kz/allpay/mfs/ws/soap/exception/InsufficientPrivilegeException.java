package kz.allpay.mfs.ws.soap.exception;

/**
 * @author magzhan.karasayev
 * @since 1/10/17 12:09 PM
 */
public class InsufficientPrivilegeException extends LocalizedException {

    private static final long serialVersionUID = -9196746632249708331L;

    final String userLogin;
    final String privilegeName;

    public InsufficientPrivilegeException(String userLogin, String privilegeName) {
        super(LocalizedException.RU_LOCALE, "kz.allpay.mfs.ws.soap.exception.InsufficientPrivilegeException", new Object[] { userLogin, privilegeName } );
        this.userLogin = userLogin;
        this.privilegeName = privilegeName;
    }
}
