import java.util.LinkedList;

interface QueueBehaviour {
    void enqueue(Person person);
    Person dequeue();
    int size();
}

interface MarketBehaviour {
    void acceptOrder(Order order);
    void completeOrder(Order order);
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Order {
    private String item;

    public Order(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}

class Market implements QueueBehaviour, MarketBehaviour {
    private LinkedList<Person> queue = new LinkedList<>();
    private LinkedList<Order> orders = new LinkedList<>();

    @Override
    public void enqueue(Person person) {
        queue.add(person);
    }

    @Override
    public Person dequeue() {
        if (!queue.isEmpty()) {
            return queue.remove();
        }
        return null;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void acceptOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void completeOrder(Order order) {
        if (orders.contains(order)) {
            orders.remove(order);
            System.out.println("Order completed: " + order.getItem());
        } else {
            System.out.println("Order not found: " + order.getItem());
        }
    }

    public void update() {
        if (!queue.isEmpty() && !orders.isEmpty()) {
            Person person = dequeue();
            Order order = orders.getFirst();
            System.out.println("Order for " + person.getName() + ": " + order.getItem());
            completeOrder(order);
        } else {
            System.out.println("No orders in the queue or no orders in the market.");
        }
    }

    public static void main(String[] args) {
        Market market = new Market();

        market.enqueue(new Person("Alice"));
        market.enqueue(new Person("Bob"));
        market.enqueue(new Person("Charlie"));

        market.acceptOrder(new Order("Apples"));
        market.acceptOrder(new Order("Bananas"));

        market.update();
        market.update();
        market.update();
    }
}