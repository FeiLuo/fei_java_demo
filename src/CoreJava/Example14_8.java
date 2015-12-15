package CoreJava;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author fluo
 *
 * Multi-thread Lock and Condition
 * 
 */
public class Example14_8 extends Example {

    public Example14_8() {
        super("Example14_8");
    }

    @Override
    public boolean doExample() {
        return false;
    }

    @Override
    public void example() {
        UnsynchBankTest14_8 t = new UnsynchBankTest14_8();
        t.test();
    }

}

class UnsynchBankTest14_8 {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    
    public void test() {
        Bank14_8 b = new Bank14_8(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable14_8 r = new TransferRunnable14_8(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}

class TransferRunnable14_8 implements Runnable {
    private Bank14_8 bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 10;
    
    public TransferRunnable14_8(Bank14_8 bank, int fromAccount, double maxAmount) {
        this.bank = bank;
        this.fromAccount = fromAccount;
        this.maxAmount = maxAmount;
    }


    @Override
    public void run() {
        try {
            while (true) {
                int toAccount = (int) (bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int)(DELAY * Math.random()));
            }
        }
        catch (InterruptedException e){
        }
    }
}


class Bank14_8 {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;
    
    public Bank14_8(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
        
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }
    
    public void transfer(int from, int to, double amout) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amout) {
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amout;
            System.out.printf(" %10.2 from %d to %d", amout, from, to);
            accounts[to] += amout;
            System.out.printf(" Total balance %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }
        finally {
            bankLock.unlock();
        }
    }

    private double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            
            for (double a : accounts) {
                sum += a;
            }
            
            return sum;
        }
        finally {
            bankLock.unlock();
        }
    }
    
    public int size() {
        return accounts.length;
    }
}

