package models;

import enumerations.TransactionForm;
import enumerations.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private TransactionType type;
    private TransactionForm transactionForm;
    private boolean isApproved;
    private String message;
}
