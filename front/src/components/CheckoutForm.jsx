import {
  PaymentElement,
  useStripe,
  useElements,
} from "@stripe/react-stripe-js";
import { useState } from "react";

export default function CheckoutForm() {
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
    }

    setCarregando(false);
  };

  return (
    <form onSubmit={handleSubmit}>
      <PaymentElement />
      <button type="submit" disabled={!stripe || carregando}>
        {carregando ? "Processando..." : "Pagar"}
      </button>
      {mensagem && <div>{mensagem}</div>}
    </form>
  );
}
