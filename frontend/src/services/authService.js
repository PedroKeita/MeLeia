export const loginUser = async ({login, senha}) => {
    const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({login, senha}),
    });

    if(!response.ok){
        throw new Error(response.statusText);
    }

    return response.json(); // isso aqui vai retornar o token jwt
}