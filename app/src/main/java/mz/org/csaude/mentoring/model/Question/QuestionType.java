package mz.org.csaude.mentoring.model.Question;

import mz.org.csaude.mentoring.model.answer.Answer;

public enum QuestionType {

    TEXT {
        @Override
        public Answer getAnswer() {
            return new Answer();
        }
    },

    BOOLEAN {
        @Override
        public Answer getAnswer() {
            return new Answer();
        }
    },

    NUMERIC {
        @Override
        public Answer getAnswer() {
            return new Answer();
        }
    };

    // Just Uncomment when ready to be used...
    // DECIMAL {
    // @Override
    // public Answer getAnswer() {
    // return null;
    // }
    // },
    //
    // CURRENCY {
    // @Override
    // public Answer getAnswer() {
    // return null;
    // }
    // };

    public abstract Answer getAnswer();
}
