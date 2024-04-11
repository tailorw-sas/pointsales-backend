package com.kynsof.identity.infrastructure.identity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WalletTransaction> transactions = new HashSet<>();

    // Métodos convenientes para manipular la billetera
    public void addTransaction(WalletTransaction transaction) {
        transactions.add(transaction);
        transaction.setWallet(this);
    }

    public void removeTransaction(WalletTransaction transaction) {
        transactions.remove(transaction);
        transaction.setWallet(null);
    }

    // Método para agregar saldo a la billetera
    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    // Método para deducir saldo de la billetera
    public void debit(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}