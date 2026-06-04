I used static variables and methods in the IdGenerator utility class. 
Because static members belong to the class itself rather than any individual instance, 
it allows the application to maintain a single, globally incrementing counter for IDs. 
This guarantees that every new Student, Course, or Enrollment receives a unique ID 
without needing to instantiate the generator.