package com.example.EmployeeWeb;

/*
@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setEmployeeName("John");
        employee.setEmployeeSurname("Doe");
        employee.setPhoneNumber("123456789");
        // Random methodunu çağır, random sayıyı emaile append diyorum
        employee.setEmailAddress("john.doe@example.com");
        employee.setLevel(Level.L1);


        Employee employee02 = new Employee();
        employee02.setEmployeeName("Jecn");
        employee02.setEmployeeSurname("Dex");
        employee02.setPhoneNumber("123456789");
        employee02.setEmailAddress("joecn.exc@example.com");
        employee02.setLevel(Level.L1);
      /*
        // try - catch ile hata handle edilmeli.
        employeeRepository.save(employee);
        employeeRepository.save(employee02);


         try {
            // Save the employee
             employeeRepository.save(employee);
             employeeRepository.save(employee02);
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violations
            throw new RuntimeException("Employee with the same email or ID already exists.");
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("An error occurred while saving the employee.");
        }
    }
}
*/
