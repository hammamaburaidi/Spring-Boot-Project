package com.hammam.demoTest.Service;

import com.hammam.demoTest.Model.Users;
import com.hammam.demoTest.Model.Accounts;
import com.hammam.demoTest.Repository.UsersRepository;
import com.hammam.demoTest.Repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, AccountsRepository accountsRepository) {
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void addNewUser(Users user) {
        Optional<Users> usersByTCKN = usersRepository.findUsersByTCKN(user.getTCKN());
        if (usersByTCKN.isPresent()) {
            throw new IllegalStateException("TCKN is already in use.");
        }
        usersRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userTCKN) {
        Optional<Users> foundUser = usersRepository.findUsersByTCKN(userTCKN);
        if (foundUser.isPresent()) {
            usersRepository.deleteByTCKN(userTCKN);
        } else {
            throw new IllegalStateException("User with TCKN " + userTCKN + " does not exist.");
        }
    }

    @Transactional
    public void updateUser(Long userTCKN, String name, String surname, String email) {
        Users user = usersRepository.findUsersByTCKN(userTCKN)
                .orElseThrow(() -> new IllegalStateException("User with TCKN " + userTCKN + " does not exist."));

        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }

        if (surname != null && !surname.equals(user.getUsername())) {
            user.setUsername(surname);
        }

        if (email != null && !email.equals(user.getEmail())) {
            user.setEmail(email);
        }

        usersRepository.save(user); // Save the updated user
    }

    public void createAccountForUser(Long userId, Accounts accounts) {
        Optional<Users> user = usersRepository.findById(userId);
        if (user.isPresent()) {
            accounts.setUserId(userId);
            accountsRepository.save(accounts);
        } else {
            throw new IllegalStateException("User with ID " + userId + " does not exist.");
        }
    }

    public List<Accounts> getAllAccountsForUser(Long userId) {
        return accountsRepository.findAllByUserId(userId);
    }

    public void removeAccountForUser(Long accountId) {
        if (accountsRepository.existsById(accountId)) {
            accountsRepository.deleteById(accountId);
        } else {
            throw new IllegalStateException("Account with ID " + accountId + " does not exist.");
        }
    }

    @Transactional
    public void transferBalanceBetweenAccounts(Long sourceAccountId, Long targetAccountId, double amount) {
        Accounts sourceAccount = accountsRepository.findById(sourceAccountId)
                .orElseThrow(() -> new IllegalStateException("Source account with ID " + sourceAccountId + " does not exist"));

        Accounts targetAccount = accountsRepository.findById(targetAccountId)
                .orElseThrow(() -> new IllegalStateException("Target account with ID " + targetAccountId + " does not exist"));

        if (!sourceAccount.getCurrency().equals(targetAccount.getCurrency())) {
            throw new IllegalStateException("Currency mismatch between accounts.");
        }

        if (sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);

            accountsRepository.save(sourceAccount);
            accountsRepository.save(targetAccount);
        } else {
            throw new IllegalStateException("Insufficient balance in the source account.");
        }
    }

    public Long getUserIdForAccount(Long accountId) {
        Accounts account = accountsRepository.findById(accountId)
                .orElseThrow(() -> new IllegalStateException("Account with ID " + accountId + " not found"));

        // Assuming there is a foreign key relationship between Accounts and Users
        // Retrieve the user ID associated with the account
        return account.getUserId();
    }
}
