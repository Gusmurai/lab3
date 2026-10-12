package ru.kafpin.lab3.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PersonDto {

    private Long id;
    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 50, message = "От 2 до 50 символов")
    private String lastName;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "От 2 до 50 символов")
    private String firstName;

    @Size(max = 50, message = "Максимум 50 символов")
    private String middleName;

    @NotBlank(message = "Необходимо выбрать пол")
    private String gender;

    @Min(value = 50, message = "Рост должен быть больше 50 см")
    @Max(value = 250, message = "Рост должен быть меньше 250 см")
    private int height;

    @Positive(message = "Вес должен быть положительным числом")
    @Max(value = 300, message = "Вес не может превышать 300 кг")
    private double weight;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Чтобы дата нормально передавалась с формы
    private LocalDate birthDate;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9]{11}$", message = "Номер телефона должен содержать 11 цифр")
    private String phoneNumber;

    @NotNull(message = "Укажите национальность")
    private Long nationalityId;

    @NotNull(message = "Укажите адрес")
    private Long addressId;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}