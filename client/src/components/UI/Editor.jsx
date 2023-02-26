import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import './Editor.css';

// * 순정 editor
// function Editor({ content, inputs, setInputs }) {
//   const handleText = (content) => {
//     setInputs({ ...inputs, content });
//   };

//   return <ReactQuill theme='snow' value={content} onChange={handleText} />;
// }

// * custom editor
function Editor({ content, inputs, setInputs }) {
  const modules = {
    toolbar: [
      //   [{ font: [] }],
      [{ header: [1, 2, 3, false] }],
      ['bold', 'italic', 'underline', 'strike'],
      ['blockquote', 'code-block'],
      // [{ list: 'ordered' }, { list: 'bullet' }, { indent: '-1' }, { indent: '+1' }],
      [{ list: 'ordered' }, { list: 'bullet' }],
      //   ['link', 'image'],
      [{ color: [] }, { background: [] }], // dropdown with defaults from theme
      // [{ align: [] }, { color: [] }, { background: [] }], // dropdown with defaults from theme
      ['clean']
    ]
  };

  const formats = [
    //'font',
    'header',
    'bold',
    'italic',
    'underline',
    'strike',
    'blockquote',
    'code-block',
    'list',
    'bullet',
    // 'indent',
    // 'link',
    // 'image',
    // 'align',
    'color',
    'background'
  ];

  const handleText = (content) => {
    setInputs({ ...inputs, content });
  };

  return <ReactQuill formats={formats} modules={modules} value={content} onChange={handleText} />;
}

export default Editor;
