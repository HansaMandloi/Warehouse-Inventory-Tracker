package controller;

public class WarehouseWorker extends Thread {
    private WarehouseController controller;
    private String productId;
    private int qty;

    public WarehouseWorker(WarehouseController controller, String productId, int qty) {
        this.controller = controller;
        this.productId = productId;
        this.qty = qty;
    }

    @Override
    public void run() {
        controller.fulfillOrder(productId, qty);
    }
}
