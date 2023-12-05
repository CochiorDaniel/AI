import random
import state

actiuni = ["up", "down", "right", "left"]


class Qmap:
    def __init__(self):
        self.qmap = [[{'up': 0, 'down': 0, 'right': 0, 'left': 0} for _ in range(10)] for _ in range(7)]
        self.learning_rate = 0.4
        self.discount_factor = 0.9
        self.nr_episoade = 300
        self.max_depth = 900
        self.e = 1

    def choose_action(self, s, epsilon):
        if random.uniform(0, 1) < epsilon:
            directie = random.choice(list(s.act.keys()))
        else:
            q_values = {a: self.qmap[s.cell[0]][s.cell[1]][a] for a in s.act}
            directie = max(q_values, key=q_values.get)

        state2 = state.State(s.act[directie][0], s.act[directie][1])
        return directie, state2

    def learn(self, s):
        self.e = 1
        while self.nr_episoade > 0:
            self.update_q(s)
            print(900 - self.max_depth)
            self.max_depth = 900
            self.nr_episoade -= 1
            if self.e > 0.1:
                self.e -= 0.01

    def update_q(self, s):
        if s.cell == (3, 7) or self.max_depth == 0:
            if s.cell == (3, 7):
                # print("Aciii")
                return 999999
            if self.max_depth == 0:
                return 0

        else:
            self.max_depth -= 1
        # directii = dict()
        # maxim = -9999
        # for a in actiuni:
        #     if a in s.act:
        #         value = self.qmap[s.cell[0]][s.cell[1]][a]
        #         if value > maxim:
        #             maxim = value
        # for a in actiuni:
        #     if a in s.act:
        #         next_pos = s.act[a]
        #         value = self.qmap[s.cell[0]][s.cell[1]][a]
        #         if value == maxim:
        #             directii[a] = state.State(next_pos[0], next_pos[1])
        #
        # if not directii:
        #     return 0

        #directie, state2 = random.choice(list(directii.items()))
        directie, state2 = self.choose_action(s, self.e)

        if self.max_depth > 0:
            # if state2.cell == (3, 7):
            #     #print("Reward", state2.reward)
            self.qmap[s.cell[0]][s.cell[1]][directie] += self.learning_rate * (
                    state2.reward + (self.discount_factor * self.update_q(state2)) -
                    self.qmap[s.cell[0]][s.cell[1]][directie])

        return self.qmap[s.cell[0]][s.cell[1]][directie]

    def print_policy(self):
        for i in range(len(self.qmap)):
            for j in range(len(self.qmap[i])):
                q_values = self.qmap[i][j]
                best_action = max(q_values, key=q_values.get)
                print(f"For state ({i}, {j}), best action is {best_action}")
