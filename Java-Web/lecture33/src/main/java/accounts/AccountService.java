package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;


public class AccountService {

    DBService dbService = new DBService();

    public void addUser(UserProfile user) {
        //users.put(user.getUser(), user);
        try {
            long userId = dbService.addUser(user.getUser(), user.getPwd());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


    public UserProfile getUser(String user) {
        try {
            UsersDataSet dataSet =  dbService.getUser(user);
            UserProfile result = new UserProfile(dataSet.getName(), dataSet.getPswd());
            return result;
        } catch (DBException e) {
            e.printStackTrace();
        }
        return null;

    }

}
