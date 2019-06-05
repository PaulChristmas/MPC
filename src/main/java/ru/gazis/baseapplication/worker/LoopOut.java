package ru.gazis.baseapplication.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoopOut implements Runnable {
    private static final AtomicBoolean closed = new AtomicBoolean(false);
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Queue<String> queue = null;

    public LoopOut(Queue queue) {
        this.queue = queue;
    }

	@Override
	public void run() {
		try {
			String str = "";
			while (!closed.get()) {
				if ((str = queue.poll()) != null) {
					System.out.println(str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void shutdown() {
        closed.set(true);
    }
}
