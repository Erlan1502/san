package com.sanreg.sanatorium.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "patients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Поле не может быть пустым, заполните его.")
    @Column(name = "date_of_arrival")
    private Date dateOfArrival;

    @Column(name = "arterial_pressure")
    private String arterialPressure;

    @Size(max = 50, message = "Имя не должно превышать 50 символов")
    @Size(min = 1, message = "Поле не должно быть пустым")
    @Column(name = "full_name")
    private String fullName;

    @NotNull(message = "Поле не может быть пустым, заполните его.")
    @Min(value = 1, message = "Номер комнаты должен быть больше 0" )
    @Max(value = 600, message = "Номер комнаты должен быть не больше 600" )
    @Column(name = "room")
    private int room;

    @Column(name = "sex")
    private String sex;

    @NotNull(message = "Поле не может быть пустым, заполните его.")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotEmpty(message="Это поле не должно быть пустым заполните его")
    @Column(name = "adress")
    private String adress;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "Пожалуйста используйте данный формат : X-ХХХ-ХХХ-ХХ-ХХ (Пример: 8-707-682-23-18)")
    private String phoneNumber;

    @NotNull(message = "Поле не может быть пустым, заполните его.")
    @Min(value = 1, message = "Количество дней должно быть больше 0" )
    @Max(value = 31, message = "Количество дней должно быть не больше 32" )
    @Column(name = "days_of_stay")
    private int daysOfStay;

    @NotNull(message = "Поле не может быть пустым, заполните его.")
    @Column(name = "departure_day")
    private Date depatureDay;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "procedures")
    private String procedures;
}
