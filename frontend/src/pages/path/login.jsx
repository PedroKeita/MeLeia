import {useState} from "react";
import {useRouter} from "next/router";
import {loginUser} from "../../services/authService";

const LoginPage = () => {
    const [login, setLogin] = useState("");
    const [senha, setSenha] = useState("");
    const [error, setError] = useState("");
    const router = useRouter();

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await loginUser({login, senha});
            if (response.token) {
                localStorage.setItem("token", response.token);
                router.push("/"); //revisar
            }
        } catch(error) {
            setError("Credenciais inseridas inválidas.");
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Usuário"
                    value={login}
                    onChange={(e) => setLogin(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Senha"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                />
                <button type="submit">Login</button>
            </form>
            {error && <div>{error}</div>}
        </div>
    );
}

export default LoginPage;