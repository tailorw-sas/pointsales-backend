package com.kynsof.calendar.application.command.shift.util;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
@Getter
@Setter
public class PriorityQueue implements Comparable<PriorityQueue> {
    private final int maxPriorityCount;
    private int currentLoop;
    private int priorityCount;
    private final int order;
    private AtomicInteger nextShiftCounter;
    private ConcurrentLinkedQueue<Integer> shiftQueue;

    // Getters and setters

    private String code;
    private String name;

    public PriorityQueue(String code, String name, int maxPriorityCount, int order) {
        this.code = code;
        this.name = name;
        this.maxPriorityCount = maxPriorityCount;
        this.order = order;
        reset();
    }

    public void reset() {
        this.currentLoop = 1;
        this.priorityCount = maxPriorityCount;
        this.nextShiftCounter = new AtomicInteger(0);
        this.shiftQueue = new ConcurrentLinkedQueue<>();
    }

    public int enqueueShift() {
        int incrementedNextShiftCounter = this.nextShiftCounter.incrementAndGet();
        this.shiftQueue.add(incrementedNextShiftCounter);
        return incrementedNextShiftCounter;
    }

    public int dequeueShift() {
        Integer shift = this.shiftQueue.poll();
        this.priorityCount--;
        if (this.priorityCount <= 0) {
            this.currentLoop++;
            this.priorityCount = this.maxPriorityCount;
        }
        return shift != null ? shift : -1;
    }

    public int getPendingShiftQuantity() {
        return this.shiftQueue.size();
    }

    @Override
    public int compareTo(PriorityQueue other) {
        if (this.currentLoop != other.currentLoop) {
            return Integer.compare(other.currentLoop, this.currentLoop);
        }
        if (this.priorityCount != other.priorityCount) {
            return Integer.compare(this.priorityCount, other.priorityCount);
        }
        return Integer.compare(other.order, this.order);
    }

    @Override
    public String toString() {
        return String.format("[Code: %s, Pending Shifts: %d, Loop: %d, Priority: %d, Max Priority: %d, Next Shift: %d]",
                code, shiftQueue.size(), currentLoop, priorityCount, maxPriorityCount, nextShiftCounter.get() + 1);
    }

}