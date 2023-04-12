package com.example.demo.db.model;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections">Projections</a>
 */
public interface ApplicationDetail extends ApplicationSummary
{
    String getAcl();
}
