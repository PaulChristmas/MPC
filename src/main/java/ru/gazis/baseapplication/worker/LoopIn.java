package ru.gazis.baseapplication.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoopIn implements Runnable {
    private static final AtomicBoolean closed = new AtomicBoolean(false);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Queue<String> queue = null;

    public LoopIn(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
        String str;
        while (!closed.get()) {
            try {
                if (!(str = scanner.next().trim()).equals("")) {
                    queue.add(str);
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        closed.set(true);
    }
}
