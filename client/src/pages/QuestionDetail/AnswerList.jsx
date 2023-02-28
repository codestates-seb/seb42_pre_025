import AnswerBody from './AnswerBody.jsx';

function AnswerList({ answers, handleDelete }) {
  return (
    <>
      {answers.map((answer) => {
        const { answerId, content, owner } = answer;
        const AnswerUserName = owner && owner.displayName;
        return (
          <AnswerBody
            key={answerId}
            id={answerId}
            content={content}
            userName={AnswerUserName}
            handleDelete={handleDelete}
          />
        );
      })}
    </>
  );
}

export default AnswerList;
