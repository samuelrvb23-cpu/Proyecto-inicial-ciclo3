/**
 * Solver para la maraton de la SlotMachine.
 *
 * El Solver solamente puede obtener informacion de la maquina mediante:
 * SlotMachine(n)
 * spin(wheel, steps)
 * distinctSymbols()
 */
public class SlotMachineContest
{
    /**
     * Resuelve el juego manteniendo la maquina invisible.
     *
     * @param n cantidad de ruedas
     * @return cantidad total de movimientos realizados
     */
    public int solve(int n)
    {
        SlotMachine machine = new SlotMachine(n);
        machine.makeInvisible();

        return resolver(machine, n, false);
    }

    /**
     * Resuelve el juego mostrando la maquina durante el proceso.
     * Utiliza exactamente la misma estrategia que solve().
     *
     * @param n cantidad de ruedas
     * @return cantidad total de movimientos realizados
     */
    public int simulate(int n)
    {
        SlotMachine machine = new SlotMachine(n);
        machine.makeVisible();

        return resolver(machine, n, true);
    }

    /**
     * Recorre sistematicamente las configuraciones posibles
     * de las ruedas hasta encontrar el jackpot.
     */
    private int resolver(SlotMachine machine, int n, boolean visual)
    {
        int movimientos = 0;

        /*
         * Guarda la posicion que hemos recorrido de cada rueda.
         *
         * Cada rueda tiene exactamente tres simbolos, por lo que
         * sus posiciones posibles son 0, 1 y 2.
         */
        int[] posiciones = new int[n];

        /*
         * La configuracion inicial ya podria ser un jackpot.
         */
        if (machine.distinctSymbols() == 1)
        {
            return movimientos;
        }

        /*
         * Las posiciones se recorren como un contador en base 3.
         *
         * Ejemplo con dos ruedas:
         *
         * 00
         * 10
         * 20
         * 01
         * 11
         * 21
         * 02
         * 12
         * 22
         *
         * De esta manera se visita cada combinacion posible.
         */
        while (true)
        {
            int rueda = 0;

            while (rueda < n)
            {
                /*
                 * Avanzamos una posicion en la rueda actual.
                 */
                machine.spin(rueda + 1, 1);
                movimientos++;

                posiciones[rueda]++;

                if (visual)
                {
                    pausaVisual();
                }

                /*
                 * Comprobamos si se consiguio el jackpot.
                 */
                if (machine.distinctSymbols() == 1)
                {
                    return movimientos;
                }

                /*
                 * Si la rueda todavia no llego a su tercera posicion,
                 * no necesitamos modificar ninguna otra rueda.
                 */
                if (posiciones[rueda] < 3)
                {
                    break;
                }

                /*
                 * La rueda pasa de la posicion 2 a la 0.
                 * Esto funciona como el acarreo de un contador en base 3.
                 */
                posiciones[rueda] = 0;
                rueda++;
            }
        }
    }

    /**
     * Pausa utilizada solamente para mostrar la animacion.
     */
    private void pausaVisual()
    {
        try
        {
            Thread.sleep(60);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}