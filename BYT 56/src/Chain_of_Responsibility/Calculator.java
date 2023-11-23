package Chain_of_Responsibility;

import java.util.Scanner;

interface OperationHandler {
    int handle(int x, int y, String operator);
}

class AdditionHandler implements OperationHandler {
    private OperationHandler nextHandler;
    public void setNextHandler(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public int handle(int x, int y, String operator) {
        if ("+".equals(operator)) {
            return x + y;
        } else if (nextHandler != null) {
            return nextHandler.handle(x, y, operator);
        } else {
            throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}

class SubtractionHandler implements OperationHandler {
    private OperationHandler nextHandler;
    public void setNextHandler(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public int handle(int x, int y, String operator) {
        if ("-".equals(operator)) {
            return x - y;
        } else if (nextHandler != null) {
            return nextHandler.handle(x, y, operator);
        } else {
            throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}

class MultiplicationHandler implements OperationHandler {
    private OperationHandler nextHandler;
    public void setNextHandler(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public int handle(int x, int y, String operator) {
        if ("*".equals(operator)) {
            return x * y;
        } else if (nextHandler != null) {
            return nextHandler.handle(x, y, operator);
        } else {
            throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}

class DivisionHandler implements OperationHandler {
    @Override
    public int handle(int x, int y, String operator) {
        if ("/".equals(operator)) {
            return x / y;
        } else {
            throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        System.out.println("Enter first number:");
        x = scanner.nextInt();
        System.out.println("Enter second number:");
        y = scanner.nextInt();
        System.out.println("Enter operation '+', '-', '*' or '/'");
        String operator = scanner.next();

        AdditionHandler additionHandler = new AdditionHandler();
        SubtractionHandler subtractionHandler = new SubtractionHandler();
        MultiplicationHandler multiplicationHandler = new MultiplicationHandler();
        DivisionHandler divisionHandler = new DivisionHandler();

        additionHandler.setNextHandler(subtractionHandler);
        subtractionHandler.setNextHandler(multiplicationHandler);
        multiplicationHandler.setNextHandler(divisionHandler);

        try {
            int result = additionHandler.handle(x, y, operator);
            System.out.println("Result: " + result);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}