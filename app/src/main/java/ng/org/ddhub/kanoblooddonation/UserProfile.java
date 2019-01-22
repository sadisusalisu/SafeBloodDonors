package ng.org.ddhub.kanoblooddonation;

/**
 * Created by Admin on 4/9/2018.
 */

public class UserProfile {

    public String UserAge;
    public String UserBLoodGroup;
    public String UserEmail;
    public String UserName;
    public String UserPhone;
    public String UserState;

    public UserProfile(){

    }

    public UserProfile(String userAge, String userBLoodGroup, String userEmail, String userName, String userPhone, String userState) {
        UserAge = userAge;
        UserBLoodGroup = userBLoodGroup;
        UserEmail = userEmail;
        UserName = userName;
        UserPhone = userPhone;
        UserState = userState;


    }

    public String getUserAge() {
        return UserAge;
    }

    public void setUserAge(String userAge) {
        UserAge = userAge;
    }

    public String getUserBLoodGroup() {
        return UserBLoodGroup;
    }

    public void setUserBLoodGroup(String userBLoodGroup) {
        UserBLoodGroup = userBLoodGroup;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserState() {
        return UserState;
    }

    public void setUserState(String userState) {
        UserState = userState;
    }
}
