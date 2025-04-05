
package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService
{
    private AccountDAO accountDAO;

    public AccountService()
    {
        accountDAO = new AccountDAO();
    }

    // Register user
    public Account registerAccount(Account account)
    {
        if (account.getUsername() == null || account.getUsername().isBlank())
        {
            return null;
        }

        if (account.getPassword() == null || account.getPassword().length() < 4)
        {
            return null;
        }

        // Check if username already exists
        Account existing = accountDAO.getAccountByUsername(account.getUsername());
        if (existing != null)
        {
            return null;
        }

        return accountDAO.insertAccount(account);
    }

    // Login
    public Account login(Account account)
    {
        return accountDAO.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
