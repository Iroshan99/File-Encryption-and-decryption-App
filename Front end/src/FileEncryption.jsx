import React, { useState } from 'react';
import axios from 'axios';
import './FileEncryption.css';

function FileEncryption() {
  const [selectedFile, setSelectedFile] = useState(null);
  const [encryptedFile, setEncryptedFile] = useState(null);
  const [decryptedFile, setDecryptedFile] = useState(null);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const encryptFile = async () => {
    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const response = await axios.post('/api/files/encrypt', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        responseType: 'blob' // Important: treat response as a binary Blob
      });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      setEncryptedFile(url);
    } catch (error) {
      console.error('Encryption error:', error);
    }
  };

  const decryptFile = async () => {
    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const response = await axios.post('/api/files/decrypt', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        responseType: 'blob' // Important: treat response as a binary Blob
      });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      setDecryptedFile(url);
    } catch (error) {
      console.error('Decryption error:', error);
    }
  };

  return (
    <div>
      <h2>File Encryption and Decryption</h2>
      <input type="file" onChange={handleFileChange} />
      <button onClick={encryptFile}>Encrypt File</button>
      <button onClick={decryptFile}>Decrypt File</button>

      {encryptedFile && (
        <div>
          <h3>Encrypted File</h3>
          <a href={encryptedFile} download="encrypted_file.enc">Download Encrypted File</a>
        </div>
      )}

      {decryptedFile && (
        <div>
          <h3>Decrypted File</h3>
          <a href={decryptedFile} download="decrypted_file">Download Decrypted File</a>
        </div>
      )}
    </div>
  );
}

export default FileEncryption;
