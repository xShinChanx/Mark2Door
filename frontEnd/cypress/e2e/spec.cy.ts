describe('My First Test', () => {
  it('Gets, types and asserts', () => {
    cy.visit('https://frontend-service-jl4ebnk3lq-ez.a.run.app')

    cy.contains('Login').click()

    // Should be on a new URL which
    // includes '/commands/actions'

    // Get an input, type into it
    cy.get('#email').type('adel@gmail.com')
    cy.get('#password').type('yeet')
    cy.get('#ButtonLogin').click();

    //  Verify that the value has been updated
    cy.url().should('include', '/homepageShopOwner')
  })
})