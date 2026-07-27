function AddShortUrl (props){

    return (

        
        <div className="w-full max-w-md bg-slate-800 rounded-2xl shadow-2xl p-6">

       {/*  <div className="flex justify-center mb-6">
          <img
            src=""
            alt="banner"
            className="w-40 h-40 object-cover rounded-xl"
          />
        </div> */}

        <h1 className="text-2xl font-bold text-center text-slate-100 mb-6">SHORT-URL</h1>

        <input 
        className="w-full p-3 rounded-lg bg-slate-700 text-white placeholder-slate-400
                     outline-none focus:ring-2 focus:ring-cyan-400 mb-4"
        type="text" 
        placeholder="Digite a URL"
        onChange={props.handleChange}
        value={props.url}/>

        <button
        className="w-full bg-cyan-500 hover:bg-cyan-400 text-white font-semibold
                     py-3 rounded-lg transition"
        onClick={() => {
            props.handleSubmit()}}>Encurtar</button>

            {(props.error?.length ?? 0) > 0 && (
                <div className="mt-5 space-y-2">
                    {props.error.map((msg, index) => (
                    <div
                        key={index}
                        className="w-full p-4 rounded-xl bg-red-500/10 border border-red-500/30
                                text-red-300 text-center text-lg font-medium
                                shadow-md"
                    >
                        {msg}
                    </div>
                    ))}
                </div>
                )}

            {props.shortUrl && (

            <div className="mt-6">

                <p className="text-slate-300 text-center mb-3 text-lg">Sua URL encurtada:</p>

                <a
                    href={props.shortUrl}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="block w-full p-4 rounded-xl bg-cyan-500/10 border border-cyan-400/30
                            text-cyan-300 text-center text-lg font-semibold
                            shadow-md hover:bg-cyan-500/20 transition
                            break-all"
                >

                    {props.shortUrl}

                </a>

            </div>

        )}
        </div>

        


    )

}

export default AddShortUrl;