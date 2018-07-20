package spring.database.jpa.netgloo.jpa1.dao;

import javax.transaction.Transactional;

import spring.database.jpa.netgloo.jpa1.models.Company;

@Transactional
public interface CompanyRepository extends UserBaseRepository<Company>{

}
