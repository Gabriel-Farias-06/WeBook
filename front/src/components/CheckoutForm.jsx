import {
  PaymentElement,
  useStripe,
  useElements,
} from "@stripe/react-stripe-js";
import "../../public/css/payments.css";
import { useState } from "react";

export default function CheckoutForm({ aoClique, idLivro, idUsuario }) {
  const stripe = useStripe();
  const elements = useElements();
  const [carregando, setCarregando] = useState(false);
  const [mensagem, setMensagem] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!stripe || !elements) return;

    setCarregando(true);

    const { error, paymentIntent } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:3000/sucesso",
      },
      redirect: "if_required",
    });

    if (error) {
      setMensagem(error.message);
    } else if (paymentIntent.status === "succeeded") {
      setMensagem("Pagamento confirmado com sucesso!");
      const response = await fetch(
        `https://webook-8d4j.onrender.com/api/usuario/${idUsuario}/livros/${idLivro}`,
        {
          method: "POST",
        }
      );

      console.log(await response.json());
    }

    setCarregando(false);
  };

  return (
    <div className="modal" onClick={aoClique}>
      <form onSubmit={handleSubmit} className="modal-content">
        <PaymentElement />
        <button type="submit" disabled={!stripe || carregando}>
          {carregando ? "Processando..." : "Pagar"}
        </button>
        {mensagem && <div>{mensagem}</div>}
      </form>
    </div>
  );
}
