class Info {

    public static void printCurrentThreadInfo() {
        String name = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        System.out.println("name: " + name);
        System.out.println("priority: " + priority);
    }
}