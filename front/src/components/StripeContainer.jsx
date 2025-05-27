import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "./CheckoutForm";

const stripePromise = loadStripe(
  "pk_test_51RTOLcBCTtyKhyoGfrv5YE70bTSpzD4xBGLopacRNzQMPO8eDaFxW3bU818oRLzq0JK5UN3cK942LB93T4gawMea00wxqmhomP"
);

export default function StripeContainer({ clientSecret }) {
  const options = {
    clientSecret,
  };

  return (
    <Elements stripe={stripePromise} options={options}>
      <CheckoutForm />
    </Elements>
  );
}
