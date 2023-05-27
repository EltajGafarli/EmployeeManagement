package com.example.employeemanagment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(name = "EMAIL_UK", columnNames = "email"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name", length = 50)
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String firstName;

    @Column(name = "last_name", length = 50)
    @NotBlank(message = "lastName cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String lastName;

    @Column(name = "email", length = 50)
    @Email(message = "email should be valid")
    private String email;

    @Column(name = "company_name", length = 50)
    @NotBlank(message = "companyName cannot be blank")
    @NotNull(message = "companyName cannot be null")
    private String companyName;

    @Column(name = "salary")
    @PositiveOrZero(message = "salary must be zero or positive")
    private int salary;
}
