import Qmap
import state


def main():
    q = Qmap.Qmap()
    s = state.State(3, 0)
    q.learn(s)
    print(q.qmap)
    q.print_policy()


if __name__ == '__main__':
    main()