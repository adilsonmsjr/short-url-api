import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import AddShortUrl from './components/AddShortUrl'

function App() {
  const [url, setUrl] = useState("");
  const [shortUrl, setShortUrl] = useState("");
  const [error, setError] = useState([]);

  
  const handleChange = ((e) => setUrl(e.target.value)); 
 
    const handleSubmit = async () => {

      console.log("Chamando backend...");

      setError([]);

      try{

      const response = await fetch("http://localhost:8080/api/create-url",
      {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
      },

      body: JSON.stringify({requestUrl: url}),
      })
    

    if (!response.ok) {

        let errorData = {};

        try {
          errorData = await response.json();
        } catch (e) {
          errorData = {};
        }

        setError(
          Array.isArray(errorData?.message)
            ? errorData.message
            : ["Erro na requisição"]
        );

        return;
      }

      const data = await response.json();

      setShortUrl(data.shortUrl);
    }

    catch (err){
      
    setError(["Não foi possível conectar ao servidor"]);

    }};
  

  return (
    <div  className="w-full min-h-screen flex items-center justify-center bg-slate-900 px-4">
    <AddShortUrl url={url} 
                handleChange={handleChange} 
                handleSubmit={handleSubmit} 
                shortUrl={shortUrl}
                error={error}
                />
    
    </div>
  )
}



export default App;
