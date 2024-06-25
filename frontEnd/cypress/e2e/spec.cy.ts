describe('End2End Test - SignIn', () => {
  it('Gets, types and asserts', () => {
    cy.visit('https://frontend-service-jl4ebnk3lq-ez.a.run.app')

    cy.contains('Login').click()

    cy.get('#email').type('anis@gmail.com')
    cy.get('#password').type('yeet')
    cy.get('#ButtonLogin').click();

    cy.url().should('include', '/homepageCustomer')
  })
})