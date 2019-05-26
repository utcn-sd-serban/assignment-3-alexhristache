describe("The question list", function() {

    beforeEach(function() {
        cy.request("http://localhost:8080/test/reseed")
            .its("status").should("be.equal", 200);
    });

    it("should show 10 questions", function() {
        cy.visit("/");
    
        cy.get('[data-cy="question"]').should("have.length", 10);
    });

    it("should add a new question", function() {
        cy.visit("/");

        cy.get('[data-cy="addQuestion"]').first().click();
        cy.get('[data-cy="questionTitle"]').type("Test Title");
        cy.get('[data-cy="questionText"]').type("Test Text");
        cy.get('[data-cy="questionTags"]').type("test_tag1, test_tag2, test_tag3");
        cy.get('[data-cy="createQuestion"]').click();
        cy.get('[data-cy="question"]').should("have.length", 11);
    });
})