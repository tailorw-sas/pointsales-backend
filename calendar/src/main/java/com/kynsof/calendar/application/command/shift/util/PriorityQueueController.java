package com.kynsof.calendar.application.command.shift.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityQueueController {
    private final List<PriorityQueue> priorityQueueList = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Response addPriorityQueue(String code, String name, int maxPriorityCount, int order) {
        Response response = validateNewPriorityQueue(code, name, maxPriorityCount);
        if (response.getCode() != ResponseCode.SUCCESS) {
            return response;
        }
        priorityQueueList.add(new PriorityQueue(code, name, maxPriorityCount, order));
        return response;
    }

    public Response<Integer> queueShift(String queueCode) {
        PriorityQueue priorityQueue = priorityQueueList.stream()
                .filter(p -> p.getCode().equalsIgnoreCase(queueCode))
                .findFirst()
                .orElse(null);
        if (priorityQueue == null) {
            return new Response<>(ResponseCode.WARNING, "Queue not found");
        }
        int shiftCounter = priorityQueue.enqueueShift();
        return new Response<>(ResponseCode.SUCCESS, "", shiftCounter);
    }

    public Response<Tuple<String, Integer>> dequeueShift() {
        lock.lock();
        try {
            if (priorityQueueList.isEmpty() || priorityQueueList.stream().allMatch(p -> p.getPendingShiftQuantity() == 0)) {
                return new Response<>(ResponseCode.WARNING, "All queues are empty");
            }
            PriorityQueue priorityQueue = priorityQueueList.stream()
                    .filter(p -> p.getPendingShiftQuantity() > 0)
                    .max(PriorityQueue::compareTo)
                    .orElse(null);
            if (priorityQueue == null) {
                return new Response<>(ResponseCode.WARNING, "No available shifts");
            }
            int shift = priorityQueue.dequeueShift();
            return new Response<>(ResponseCode.SUCCESS, "", new Tuple<>(priorityQueue.getCode(), shift));
        } finally {
            lock.unlock();
        }
    }

    public void reset() {
        priorityQueueList.forEach(PriorityQueue::reset);
    }

    public void print() {
        System.out.println("**************************************************************************");
        priorityQueueList.forEach(System.out::println);
        System.out.println("**************************************************************************");
    }

    private Response validateNewPriorityQueue(String code, String name, int maxPriorityCount) {
        if (code == null || code.isEmpty()) {
            return new Response(ResponseCode.ERROR, "Invalid queue code");
        }
        if (priorityQueueList.stream().anyMatch(p -> p.getCode().equalsIgnoreCase(code))) {
            return new Response(ResponseCode.WARNING, "There is already a queue with that code");
        }
        if (name == null || name.isEmpty()) {
            return new Response(ResponseCode.ERROR, "Invalid queue name");
        }
        if (priorityQueueList.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name))) {
            return new Response(ResponseCode.WARNING, "There is already a queue with that name");
        }
        if (maxPriorityCount <= 0) {
            return new Response(ResponseCode.ERROR, "Max Priority Count must be greater than 0");
        }
        return new Response(ResponseCode.SUCCESS, "");
    }
}