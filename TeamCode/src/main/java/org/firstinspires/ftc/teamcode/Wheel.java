package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.AnalogInput;

public class Wheel {

    static double conversionFactor = ((2 * Math.PI) / 3.3);

    private DcMotor speedMotor;
    private CRServo angleMotor;

    private AnalogInput encoder;

    private PID pid = new PID(PID.Type.move);

    private double lastAngle;

    public double calculateError(double current, double target) {

        double error1;
        double error2;

        if (current > target) {
            error2 = (target + 2 * Math.PI) - current;
        } else {
            error2 = (target - 2 * Math.PI) - current;
        }

        error1 = target - current;
        return Math.abs(error1) > Math.abs(error2) ? error2 : error1;
    }

    public Wheel(DcMotor speed, CRServo angle, AnalogInput enc) {
        speedMotor = speed;
        angleMotor = angle;
        encoder = enc;

        PID.setConstantsMove(0, 0, 0);
    }

    public void drive(double speed, double angle) {
        if (angle != lastAngle) {
            pid.reset();
            lastAngle = angle;
        }

        double currentAngle = encoder.getVoltage() * conversionFactor;

        double error = calculateError(currentAngle, angle);

        speedMotor.setPower (speed);

        pid.pid(error);

        angleMotor.setPower (pid.pidout);

//        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); // Optimization offset can be calculated here.
//
//        if (setpoint < 0) {
//            setpoint = MAX_VOLTS + setpoint;
//        }
//        if (setpoint > MAX_VOLTS) {
//            setpoint = setpoint - MAX_VOLTS;
//        }

//        pidController.setSetpoint (setpoint)
    }


}
