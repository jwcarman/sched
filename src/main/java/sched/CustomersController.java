package sched;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
class CustomersHttpController {

    private final CustomerRepository repository;

    @GetMapping("/customers")
    public List<Customer> customers() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    public CustomerDto addCustomer(@RequestBody CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        repository.save(customer);
        return new CustomerDto(customer.getId(), customer.getName());
    }

    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable String id, @RequestBody CustomerDto dto) {
        final Customer existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        existing.setName(dto.name());
        repository.save(existing);
        return new CustomerDto(existing.getId(), existing.getName());
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable String id) {
        repository.deleteById(id);
    }

    public record CustomerDto(String id, String name) {
    }


}