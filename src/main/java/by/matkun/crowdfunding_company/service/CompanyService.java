package by.matkun.crowdfunding_company.service;

import by.matkun.crowdfunding_company.model.Company;

import java.util.List;

public interface CompanyService {
    Company find(Long id);

    List<Company> findAll();

    Company save(Company company);

    void delete (Long id);
}
