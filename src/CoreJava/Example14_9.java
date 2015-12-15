package CoreJava;

/**
 * 
 * @author fluo
 *
 *  1. multi-thread synchronized
 *  
 *  
 */
public class Example14_9 extends Example {

    public Example14_9() {
        super("Example14_9");
    }

    @Override
    public boolean doExample() {
        return false;
    }

    @Override
    public void example() {
        UnsynchBankTest14_9 t = new UnsynchBankTest14_9();
        t.test();
    }
}


class UnsynchBankTest14_9 {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    
    public void test() {
        Bank14_9 b = new Bank14_9(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable14_9 r = new TransferRunnable14_9(b, i, INITIAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}

class TransferRunnable14_9 implements Runnable {
    private Bank14_9 bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 10;
    
    public TransferRunnable14_9(Bank14_9 bank, int fromAccount, double maxAmount) {
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


class Bank14_9 {
    private final double[] accounts;
    
    public Bank14_9(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }
    
    public synchronized void transfer(int from, int to, double amout) throws InterruptedException {
        while (accounts[from] < amout) {
            wait();
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amout;
        System.out.printf(" %10.2 from %d to %d", amout, from, to);
        accounts[to] += amout;
        System.out.printf(" Total balance %10.2f%n", getTotalBalance());
        notifyAll();
    }

    private synchronized double getTotalBalance() {
        double sum = 0;
        
        for (double a : accounts) {
            sum += a;
        }
        
        return sum;
    }
    
    public int size() {
        return accounts.length;
    }
}

