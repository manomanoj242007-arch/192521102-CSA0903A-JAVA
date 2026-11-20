class ScannerStation extends Thread {
    private final int scripts;

    ScannerStation(String name, int scripts) {
        super(name);
        this.scripts = scripts;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " started");
            Thread.sleep(100L * scripts);
            System.out.println(getName() + " scanned " + scripts + " scripts");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " interrupted");
        }
    }
}

class RunnableStation implements Runnable {
    private final String station;
    private final int scripts;

    RunnableStation(String station, int scripts) {
        this.station = station;
        this.scripts = scripts;
    }

    @Override
    public void run() {
        try {
            System.out.println(station + " started");
            Thread.sleep(100L * scripts);
            System.out.println(station + " scanned " + scripts + " scripts");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ScannerStationDemo {
    public static void main(String[] args) throws InterruptedException {
        ScannerStation s1 = new ScannerStation("Station-1", 2);
        ScannerStation s2 = new ScannerStation("Station-2", 3);
        Thread s3 = new Thread(new RunnableStation("Station-3", 1));
        Thread s4 = new Thread(new RunnableStation("Station-4", 4));
        Thread[] stations = {s1, s2, s3, s4};

        for (Thread station : stations) station.start();
        for (Thread station : stations) station.join();
        System.out.println("All stations completed.");
    }
}