package com.hammam.demoTest.Controller;
import com.hammam.demoTest.Model.Accounts;
import com.hammam.demoTest.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountsController {

    private final UsersService usersService;

    @Autowired
    public AccountsController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(path = "{userId}/accounts")
    public void createAccountForUser(
            @PathVariable("userId") Long userId,
            @RequestBody Accounts account) {
        usersService.createAccountForUser(userId, account);
    }

    @GetMapping(path = "{userId}/accounts")
    public List<Accounts> getAllAccountsForUser(@PathVariable("userId") Long userId) {
        return usersService.getAllAccountsForUser(userId);
    }

    @DeleteMapping(path = "{userId}/accounts/{accountId}")
    public void removeAccountForUser(
            @PathVariable("userId") Long userId,
            @PathVariable("accountId") Long accountId) {
        usersService.removeAccountForUser(accountId);
    }

//    @PostMapping(path = "{userId}/accounts/transfer")
//    public void transferBalanceBetweenAccounts(
//            @PathVariable("userId") Long userId,
//            @RequestParam(required = true) Long sourceAccountId,
//            @RequestParam(required = true) Long targetAccountId,
//            @RequestParam(required = true) double amount) {
//
//        usersService.transferBalanceBetweenAccounts(sourceAccountId, targetAccountId, amount);
//    }

//    @PostMapping(path = "{userId}/accounts/transfer")
//    public ResponseEntity<String> transferBalanceBetweenAccounts(
//            @PathVariable("userId") Long userId,
//            @RequestParam(required = true) Long sourceAccountId,
//            @RequestParam(required = true) Long targetAccountId,
//            @RequestParam(required = true) double amount) {
//
//        try {
//            usersService.transferBalanceBetweenAccounts(sourceAccountId, targetAccountId, amount);
//            return ResponseEntity.ok("Transfer successful");
//        } catch (IllegalStateException e) {
//            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
//        }
//    }

    @PostMapping(path = "{userId}/accounts/transfer")
    public ResponseEntity<String> transferBalanceBetweenAccounts(
            @PathVariable("userId") Long userId,
            @RequestParam(required = true) Long sourceAccountId,
            @RequestParam(required = true) Long targetAccountId,
            @RequestParam(required = true) double amount) {

        try {
            usersService.transferBalanceBetweenAccounts(sourceAccountId, targetAccountId, amount);

            // Fetch the user IDs associated with the source and target accounts
            Long sourceUserId = usersService.getUserIdForAccount(sourceAccountId);
            Long targetUserId = usersService.getUserIdForAccount(targetAccountId);

            // Check if the source and target accounts belong to different users
            if (!sourceUserId.equals(targetUserId)) {
                return ResponseEntity.ok("Transfer successful between two different accounts.");
            } else {
                return ResponseEntity.ok("Transfer successful");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }



}
