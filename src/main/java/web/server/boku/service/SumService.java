package web.server.boku.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import web.server.boku.dto.InputDto;

@Service
public class SumService {

    private List<Integer> numbers = new ArrayList<>();
    private AtomicInteger requestCount = new AtomicInteger(0);

    public synchronized String handleRequest(InputDto request) {

        String response;
        String value = request.getInputValue();

        if (value.startsWith("end ")) {
            String id = value.substring(4);

            requestCount.incrementAndGet();

            int sum = numbers.stream().mapToInt(Integer::intValue).sum();
            response = sum + " " + id;
            numbers.clear();
            requestCount.set(0);
            return response;

        } else {
            try {

                int number = Integer.parseInt(value);
                numbers.add(number);
                response = " "; // "Number " + number + " received." Commented since we dont have to show any
                                // results

                return response;

            } catch (NumberFormatException e) {
                response = "Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum.";
                return response;
            }

        }
    }
}