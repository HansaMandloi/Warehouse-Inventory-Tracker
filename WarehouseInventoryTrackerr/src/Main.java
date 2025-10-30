import controller.WarehouseController;
import model.FileStorage;
import model.Warehouse;
import observer.AlertService;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        FileStorage storage = new FileStorage("inventory.txt");
        Warehouse warehouse = new Warehouse(storage);
        warehouse.addObserver(new AlertService());

        WarehouseController controller = new WarehouseController(warehouse);
        ConsoleView view = new ConsoleView(controller);

        view.showMenu();
    }
}

