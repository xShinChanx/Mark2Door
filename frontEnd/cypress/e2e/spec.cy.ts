describe('End2End Test - SignIn', () => {
  it('Gets, types and asserts', () => {
    cy.visit('https://frontend-service-jl4ebnk3lq-ez.a.run.app')

    cy.contains('Login').click()

    // Should be on a new URL which
    // includes '/commands/actions'
  
    cy.get('#email').type('anis@gmail.com')
    cy.get('#password').type('yeet')
    cy.get('#ButtonLogin').click();

    //  Verify that the value has been updated yes1
    cy.url().should('include', '/homepageCustomer')
  })
})