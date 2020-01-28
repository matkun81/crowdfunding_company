package by.matkun.crowdfunding_company.service;

import by.matkun.crowdfunding_company.dao.CompanyRepository;
import by.matkun.crowdfunding_company.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService  {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company find(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
